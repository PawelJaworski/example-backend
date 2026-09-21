# Events

Events are facts that have happened. File order = chronological order; time
flows left to right in the diagram. Use `Subprocess:` to group related events
into the same horizontal band. Every event must declare `{aggregateName}:Id`.
One heading per event.

## products-added-to-store
store:Id
Name: Products Added to Store
Subprocess: Store
* product (List)
* * code
* * name
* * description
