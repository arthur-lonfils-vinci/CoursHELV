using Microsoft.EntityFrameworkCore;

namespace ServiceOrder.Models
{
    public class OrderContext(DbContextOptions<OrderContext> options) : DbContext(options)
    {
        public DbSet<OrderItem> Orders => Set<OrderItem>();
    }
}
