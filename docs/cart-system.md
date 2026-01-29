### Cart System Documentation (Creation → Retrieval → Update)

Last updated: 2026-01-28 09:05

This document explains how the Shopping Cart subsystem works in this project, including data model, DTOs, endpoints, validations, and example payloads.

---

### High-level overview
- A ShoppingCart belongs to one AppUser (one-to-one).
- A ShoppingCart contains zero or more ShoppingCartItem entries (one-to-many).
- Items are persisted/removed together with the cart (cascade = ALL, orphanRemoval = true).
- Clients can:
  - Create a cart for a user, optionally with initial items.
  - Retrieve a cart by id and list carts.
  - Update a cart by fully replacing its items.

---

### Data model
- Entity: ShoppingCart (extends BaseEntity)
  - id: UUID (from BaseEntity)
  - createdAt, updatedAt: LocalDateTime (from BaseEntity)
  - user: AppUser (OneToOne)
  - items: List<ShoppingCartItem> (OneToMany, mappedBy = "cart", cascade = ALL, orphanRemoval = true)

- Entity: ShoppingCartItem (extends BaseEntity)
  - id, createdAt, updatedAt
  - cart: ShoppingCart (ManyToOne, not null)
  - productItem: ProductItem (ManyToOne, not null)
  - quantity: Integer (>= 1)

Notes
- Cascade + orphanRemoval ensure that when the cart is saved, new/updated items are saved; when an item is removed from the items list, it is deleted on save.

---

### DTOs
- CreateCartRequest
  - userId: UUID (required)
  - items: List<CreateCartItemRequest> (optional)

- CreateCartItemRequest
  - productItemId: UUID (required)
  - quantity: Integer (required, >= 1)

- UpdateCartRequest
  - items: List<CreateCartItemRequest>
    - Full replacement semantics: the cart's items will be cleared and replaced with this list. If null/empty, the cart becomes empty.

- CartResponse
  - id: UUID
  - createdAt, updatedAt: LocalDateTime
  - userId: UUID
  - items: List<CartItemResponse>

- CartItemResponse
  - id: UUID (ShoppingCartItem id)
  - productItemId: UUID
  - quantity: Integer

---

### Endpoints
Base path: /api/v1/carts

1) Create cart
- POST /api/v1/carts
- Request body (CreateCartRequest):
  {
    "userId": "<USER_UUID>",
    "items": [
      { "productItemId": "<PRODUCT_ITEM_UUID>", "quantity": 2 },
      { "productItemId": "<PRODUCT_ITEM_UUID_2>", "quantity": 1 }
    ]
  }
- Responses:
  - 200 OK: ApiResponse<CartResponse> with created cart and items
  - 400 Bad Request: missing userId, invalid quantity, missing productItemId
  - 404 Not Found: user or product item not found

2) Get cart by id
- GET /api/v1/carts/{id}
- Responses:
  - 200 OK: ApiResponse<CartResponse>
  - 404 Not Found: cart not found

3) List carts
- GET /api/v1/carts?limit=10&offset=0
- Responses:
  - 200 OK: ApiResponse<List<CartResponse>>

4) Update cart (replace items)
- PUT /api/v1/carts/{id}
- Request body (UpdateCartRequest):
  {
    "items": [
      { "productItemId": "<PRODUCT_ITEM_UUID>", "quantity": 3 },
      { "productItemId": "<PRODUCT_ITEM_UUID_3>", "quantity": 1 }
    ]
  }
- Behavior:
  - Replaces entire items list. If items is null/empty, the cart ends up empty.
- Responses:
  - 200 OK: ApiResponse<CartResponse>
  - 400 Bad Request: invalid quantity, missing productItemId
  - 404 Not Found: cart or product item not found

---

### Validation and error handling
- CreateCartRequest.userId is required.
- Each CreateCartItemRequest requires productItemId and quantity >= 1.
- If a referenced ProductItem does not exist, the API returns 404.
- Not found cases produce ApiResponse.notFound(...), returned with HTTP 404.
- Bad request cases produce ApiResponse.error(message), returned with HTTP 400.

---

### Persistence behavior
- The controller constructs ShoppingCartItem entities and adds them to the ShoppingCart.items list before saving.
- ShoppingCartService delegates to ShoppingCartRepository.save(cart).
- Because of cascade = ALL and orphanRemoval = true:
  - New items are saved when the cart is saved.
  - Removing an item from the items list and saving the cart deletes the orphan item.

---

### Example end-to-end flow
1) Create cart
POST /api/v1/carts
{
  "userId": "2f0f2a5d-2e1a-4b2a-bd8e-0b2c0a3d0c1f",
  "items": [ { "productItemId": "c1e9...", "quantity": 2 } ]
}

2) Retrieve cart
GET /api/v1/carts/{cartId}

3) Update cart
PUT /api/v1/carts/{cartId}
{
  "items": [ { "productItemId": "c1e9...", "quantity": 3 } ]
}

---

### Code touchpoints
- Controller: domain/cart/controller/CartController.java
- Entities: domain/cart/entity/ShoppingCart.java, domain/cart/entity/ShoppingCartItem.java
- DTOs: domain/cart/dto/*.java
- Mapper: domain/cart/mapper/CartMapper.java
- Service: domain/cart/service/ShoppingCartService.java, impl/ShoppingCartServiceImpl.java

---

### Notes and future improvements
- Add an endpoint to append/update a single item (PATCH semantics) to avoid full replacements.
- Optionally enforce one-cart-per-user invariant in the service/repository layer.
- Include product snapshot info (name/price) inside CartItemResponse if desired.
- Implement pagination for list endpoint using PageResponse for consistency with users.
