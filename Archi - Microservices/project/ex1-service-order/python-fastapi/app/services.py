from app.model import Order, OrderDTO

orderlist: list[Order] = []

async def getOrdersService():
	return orderlist

# return an Order with an id
async def createOrderService(order: OrderDTO) -> dict:
  id = len(orderlist) + 1
  order = Order(id=id, **order.model_dump())
  orderlist.append(order)
  id_item = orderlist[id - 1].id
  return {"id": f"{id_item}"}

async def getOrderService(order_id: int) -> Order | dict:
	for order in orderlist:
		if order.id == order_id:
			return order
	return {"error": "Order not found"}