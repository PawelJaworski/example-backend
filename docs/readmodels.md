# Read Models

Read models are projections derived from events. `Subscribes:` takes a
comma-separated list of event ids from events.md. One heading per read model.

Every read model must declare at least one of `{aggregateName}:Id` or one or
more `{keyName}:Key` lines (see SKILL.md) — a read model with neither is a
hard error.

Note: `attribute:Id` and `attribute:Key` have special meanings (identifiers/keys)
and are rendered as bold lines under the card title. You can also add these
attributes as normal field attributes with different naming (e.g., `attributeId`
or `attribute key`) — these will be rendered as regular bullet points.

## stored-product
productCode:Key
Name: Product
Subscribes: products-added-to-store
    * product code
* product name
* product description