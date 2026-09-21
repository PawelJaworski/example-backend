# Commands

Commands are the actions actors ask the system to perform. Each command is an
`## heading` whose text is its id. One heading per command.

## add-products-to-store
store:Id
Name: Add Products to Store
Produces: products-added-to-store
* product (List)
* * code
* * name
* * description
