namespace ServiceOrder.Models
{
    public class OrderItem
    {
        public long Id { get; set; }
        public string? CustomerName { get; set; }
        public OrderLineItem[]? OrderLines { get; set; }
        public decimal TotalAmount { get; set; }
    }

    public class OrderItemDto
    {
        public string? CustomerName { get; set; }
        public OrderLineItem[]? OrderLines { get; set; }
    }

    public class OrderLineItem
    {
        public long Id { get; set; }
        public string? ProductName { get; set; }
        public int Quantity { get; set; }
        public decimal Price { get; set; }
    }
}
