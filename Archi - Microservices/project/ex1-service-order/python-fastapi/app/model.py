from pydantic import BaseModel

class OrderLine(BaseModel):
    id: int
    product_name: str
    quantity: int
    total_price: float
    
class Order(BaseModel):
		id: int
		customer_name: str
		order_lines: list[OrderLine]
		total_amount: float
  
class OrderDTO(BaseModel):
		customer_name: str
		order_lines: list[OrderLine]
		total_amount: float
 