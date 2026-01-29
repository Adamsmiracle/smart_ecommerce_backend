### Review creation request body examples

- Minimal valid request body:

```
{
  "user": { "id": "<USER_UUID>" },
  "orderedProduct": { "id": "<ORDER_LINE_UUID>" },
  "ratingValue": 5
}
```

- Full example with optional comment:

```
{
  "user": { "id": "<USER_UUID>" },
  "orderedProduct": { "id": "<ORDER_LINE_UUID>" },
  "ratingValue": 4,
  "comment": "Great quality and fast delivery."
}
```

Notes:
- ratingValue must be an integer between 1 and 5.
- comment is optional; max 1000 characters.
- orderedProduct.id refers to the OrderLine (the purchased item being reviewed).
