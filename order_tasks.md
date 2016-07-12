#tasks
2. create a product. using form data (name, description, price) as input. (201)
1. get product detail according to product id. response the json of product details.
1. get products list. if succeed, return the json of product list, including the description for each product.(200)
3. create an order for some user. using json as input. 
4. get an order detail of some user according to orderID. response the json of order detail(200)
5. get all the orders of some user. response the json of all the orders. (200)
6. pay for an order of some user. use form data (pay_type, amount) as the input.
7. get the payment information of some roder of some user. response the json of the information (200)

#databases
1. product: 
	2. name
	3. description
	4. price
	5. rating
2. order:
	1. user_id
	3. name
	4. address
	5. phone
	7. created_at
	9. pay_state
7. order_items:
	8. order_id
	8. product_id
	9. quantity
	10. amount
10. user:
	11. name
12. payments:
	8. pay_type
	10. pay_at
	11. pay_amount

	



