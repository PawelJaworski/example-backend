# AGENTS.md

## Paths
| Key | Path | Used by |
|-----|------|---------|
| `docs` | `modelDir` in `codegen.config.json` (this project: `docs/`) | All skills and agents |
| `eventModel` | `{docs}` | event-modelling, architect, development-team |

## Codegen
Scaffolding is **generated deterministically** by plugins — never hand-written.

```
node .opencode/skills/backend-development/scripts/codegen            # regenerate from model
node .opencode/skills/backend-development/scripts/codegen --check    # CI gate: fail if stale
node .opencode/skills/backend-development/scripts/codegen --patch    # model -> code diff
node .opencode/skills/backend-development/scripts/codegen --json     # print parsed model
node .opencode/skills/backend-development/scripts/codegen --next     # pick next step + render prompt
node .opencode/skills/backend-development/scripts/codegen --prompt <STEP>  # render ONE prompt
node .opencode/skills/backend-development/scripts/codegen --test     # print step machine
```

**Drive the work, don't carry it:**
```
node .opencode/skills/backend-development/scripts/codegen --next --json
```

Source of truth: `{docs}/{commands,events,readmodels}.md` + `business-definitions-raw.md`, where `{docs}` is `modelDir` from `codegen.config.json`.

**API contract.** After codegen touches a command or read model, export OpenAPI:
```
mvn verify            # boots app, writes api/openapi.json
```
Never commit `api/openapi.json`; regenerate it.

**Advisory from `--check`** lists hand-owned files whose logic diverges from the model.
The agent MAY add a missing member. The agent MUST NOT rewrite existing logic.
Report drift; do not resolve it.

## Ad-hoc extensions (no model change)
Implementation improvements over existing fields — not model changes.

- Do **not** edit `{docs}/*.md` or `scripts/codegen/*`
- Still **TDD**: Spock spec first, then minimal code
- Code goes into the slice's generated projector/repository as **added** members
- A new *field* or *event* is NOT ad-hoc — escalate to architect
- `scripts/codegen --check` must still report `up to date`

## Adding behavior
**TDD, never scaffolding.** Write the Spock test from the `gwt-*.md` scenario or
business rule, run it, get a loud failure, implement in the decider.

Use only generated `*Ability` DSLs — never construct handlers, projectors or
repositories in a test. Model docs are **read-only** during development.

## Build / test
- Build: `./mvnw compile`
- Test: `./mvnw clean test` (SpringBootTest + H2 in-memory)
- **Always `clean` when Lombok-annotated or generated classes changed**
- **Never use `-Dtest=`** — surefire matches `**/*Spec.class`
- Spock specs go in `src/test/groovy/`, not `src/test/java/`
- **LSP false positives**: jdtls doesn't understand Lombok here. Trust `mvn`, not the IDE.

## Stack
Java 25, Spring Boot 4.1.0, Maven, Lombok, H2. Base package: `pl.pjaworski.examplebackend`.

## Subagents
`architect` subagent owns domain modeling/API-contract decisions. Escalate business-intent ambiguity.
