from app.model import Order, OrderDTO
from fastapi import FastAPI
from app.services import getOrdersService, createOrderService, getOrderService

app = FastAPI()


@app.get("/")
async def getOrders():
    return await getOrdersService()
  
@app.post("/order/")
async def createOrder(order: OrderDTO) -> dict:
		return await createOrderService(order)

@app.get("/order/{order_id}")
async def getOrder(order_id: int) -> Order | dict:
    return await getOrderService(order_id)