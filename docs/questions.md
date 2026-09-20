# Architect questions / follow-ups

The list + inner-object syntax is now implemented in BOTH skills: the
event-modelling generator (parse/render/consistency) and the backend codegen
(parse/type-resolution/emission, incl. inline value objects and nested-path
projection). One modeling decision remains for the project's own model.

## 1. `stored-product` cannot project an attribute of a List (blocking codegen) — RESOLVED

**Decision (owner): Option C — per-element list explosion; each added product
becomes its own row.** Applied in the codegen run of 2026-09-20.

`product:Id` on the event already declares each product as its own aggregate, so
the explosion is implemented **at the command boundary** (the only shape the
grammar can express):

- `events.md`: `* product` is now a **single** inner object (the ` (List)`
  marker was dropped). One `products-added-to-store` event **per product**,
  each with its own fresh aggregate id; `commands.md` still accepts the
  `product (List)` batch; `readmodels.md` unchanged.
- The handler explodes the command's list into one event per element — a
  red-build fix of the generated `handle()` body (scaffold emits a single
  passthrough append that does not compile against the single-product event).
- Result: `GET /stored-product` returns **one row per product**
  (`productName`, `productDescription`), as C intended.

Why not the literal projector-level explosion: per-row identity is
inexpressible (`:Key` cannot be `[bracketed]`/`:convention`; derived key paths
refuse a List ancestor), and the generated projector saves exactly one row per
event — extending the generator for it is an architect-owned tooling change
(README: "a generator change, and a deliberate architect decision").

Open follow-ups (non-blocking):

- [ ] What should `POST /add-products-to-store` return for a batch — a single
      id is generated per exploded event; the endpoint currently returns the
      first product's id. Batch-identifier vs first-id is an API-contract
      decision.
- [ ] Empty-batch behavior (append nothing, return `null` body) — confirm or
      add a business rule.
- [ ] Regenerate `eventmodel.html` via the event-modelling skill: the diagram
      still draws `product (List)` on the event.

## 2. `:Key` transformation rule oddity (non-blocking)

`isTransformationOfSpecialAttribute` maps `{name}:Key` → `{name spaced} key`,
so `customerId:Key` recognizes `customer id key`, not the intuitive
`customer id`. If `customer id` is the intended transformation for a camelCase
key name, that rule (and its unit tests) should be revisited.

## Additional remarks (non-blocking)

- **Pre-existing failures in the backend script tests** (not caused by this
  work, verified): `testdata.test.js` imports `commandAbility` from `emit.js`,
  which defines but never exports it (fails at load); `get-prompt.test.js`
  asserts prompt text ("only through *Ability") that the current prompts no
  longer contain. Both predate these changes.
- **Pre-existing template inconsistencies surfaced** in the event-modelling
  `templates/` set (`policy-status`'s `* status` and `* customer id`); they
  were masked by an old parsing bug and are unrelated to this feature.
- **`business-definitions.html` / `business-rules.html` are missing from
  `docs/`**, so the event-modelling generator's actor/term check is silently
  skipped. Rendering is the business-rules-and-definitions skill's job.
- **`Product` is not a documented business term** (only the aggregate name
  `product:Id` / `product:Key`). Worth an entry in `business-definitions-raw.md`
  either way.
- **Command id/name normalization kept:** heading `add-products-to-store`,
  name `Add Products to Store` (was `add-products to store` /
  `add-products-to-store`). Revert if the original spelling was intended.