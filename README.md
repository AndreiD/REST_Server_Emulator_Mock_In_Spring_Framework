# Simple Shopping App Server REST Endpoint Emulator

- Spring Framework
- No databases.

Note: Lombok is used, so make sure you enable annotation processor.

### Usage

Download the project. run it with  <strong>./gradlew bootRun</strong>

### Endpoints 
- POST: http://localhost:8080/api/v1/login
- POST: http://localhost:8080/api/v1/signup
- GET: http://localhost:8080/api/v1/promotions
- GET: http://localhost:8080/api/v1/cart
- POST: http://localhost:8080/api/v1/cart
- POST: http://localhost:8080/api/v1/checkout

Example CART Json

~~~~
{
    "id": "2fsfsfds32",
    "created_at": 1499650674,
    "currency": "BGN",
    "store_id": 2,
    "vat": 1.82,
    "total": 10.9,
    "promo": {
        "code": "129F3",
        "product_id": 83,
        "type": "product",
        "value": "1.90",
        "text": "Free Tea 0.5 L"
    },
    "products": [
        {
            "product_type": "other",
            "qty": 2,
            "group_id": 8,
            "product_id": 82,
            "name": "Coca Cola 0.5l",
            "price": 1.9
        },
        {
        	"product_type": "pizza",
            "group_id": 6,
            "name": "Pizza 36 cm",
            "product_id": 12,
            "qty": 1,
            "price": 9
        }
    ]
}
~~~~

The MIT License

Copyright (c) 2017-Present Avid Academy

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.