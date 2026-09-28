# Translators

Translators are the "bots" that bridge the external world into the system
(Translation Pattern). They live in the `Bots` swimlane at the very top and
render as cards with a ⚙ sprocket badge — one card per produced command.

- `Type:` — optional, free-form display/transport hint. The value can be
  anything (no enum). `rest` and `kafka` additionally select a backend ingress
  adapter (a `@RestController` POST endpoint / a `@KafkaListener`); any other
  value (or none) still generates the translator as a plain `@Component`.
- `Subscribes:` — comma-separated external event ids from external-events.md.
- `Produces:` — comma-separated command ids from commands.md.
- Each translator must subscribe at least one external event and produce at
  least one command.

## translate-application
Name: Translate Application
Type: kafka
Subscribes: application-received
Produces: issue-policy