# Restaurant Client

Restaurant API ကိုအသုံးပြုမည့် Client Project ဖြစ်ပါတယ်။ Java Language ကိုအသုံးပြုထားပြီး Library အနေနဲ့ Retrofit 2 ကိုအသုံးပြုထားပါတယ်။ Restaurant GUI နဲ့ Restaurant Android Application တွေကနေ Restaurant Client ကိုအသုံးပြုပြီး API ရဲ့ Resource တွေကို အသုံးပြုမှာ ဖြစ်ပါတယ်။

![Restaurant Client Architecture](/images/client-arch.png)

အခြေခံအားဖြင့် Restaurant Client Application မှာ အစိတ်ပိုင်း ၃ မျိုုးပါဝင်ပါတယ်။ 
1. Data Transfer Object
2. Client API Interfaces
3. Client Factory Class

## Data Transfer Object

API ရဲ့ Resource တွေကို ကိုယ်စားပြုတဲ့ POJO Class တွေဖြစ်ကြတယ်။ ပါဝင်တဲ့ Class တွေကတော့ အောက်ပါအတိုင်းဖြစ်ပါတယ်။

![DTO Class Diagram](/images/dto-class-relation.png)

## Client API Interfaces

သက်ဆိုင်ရာ Resource API တွေကို Access လုပ်နိုင်တဲ့ Interface တွေဖြစ်ပါတယ်။ Retrofit ကို အသုံးပြုထားတဲ့ အတွက် Implementation Class ကို ရေးစရာမလိုပါဘူး။

[EmployeeApi](src/main/java/com/jdc/restaurant/client/api/EmployeeApi.java)
```java
public interface EmployeeApi {
	
	@POST("/employees")
	Call<Employee> create(@Body Employee data);
	
	@PUT("/employees")
	Call<Employee> update(@Body Employee data);
	
	@GET("/employees/{id}")
	Call<Employee> findById(@Path("id") long id);
	
	@GET("/employees")
	Call<List<Employee>> findAll();
	
	@GET("/employees/search")
	Call<List<Employee>> search(@Query("name") String name);

}
```

[TableAPI](src/main/java/com/jdc/restaurant/client/api/TableApi.java)
```java
public interface TableApi{

	@POST("/tables")
	Call<Table> create(@Body Table data);
	
	@PUT("/tables")
	Call<Table> update(@Body Table data);
	
	@GET("/tables/{id}")
	Call<Table> findById(@Path("id") long id);
	
	@GET("/tables")
	Call<List<Table>> findAll();

}
```

[CategoryApi](src/main/java/com/jdc/restaurant/client/api/CategoryApi.java)
```java
public interface CategoryApi {

	@POST("/categories")
	Call<Category> create(@Body Category data);

	@PUT("/categories")
	Call<Category> update(@Body Category data);

	@GET("/categories")
	Call<List<Category>> findAll();
	
	@GET("/categories/{id}")
	Call<Category> findById(@Path("id") long id);
}
```

[MenuApi](src/main/java/com/jdc/restaurant/client/api/MenuApi.java)
```java
public interface MenuApi {

	@POST("/products")
	Call<Menu> create(@Body Menu data);

	@PUT("/products")
	Call<Menu> update(@Body Menu data);

	@GET("/products")
	Call<List<Menu>> findAll();
	
	@GET("/products/{id}")
	Call<Menu> findById(@Path("id") long id);

	@GET("/products/category/{id}")
	Call<List<Menu>> findByCategory(@Path("id") long id);
}
```

[SaleApi](src/main/java/com/jdc/restaurant/client/api/SaleApi.java)
```java
public interface SaleApi {

	@POST("/sales")
	Call<Sale> create(@Body Sale data);

	@PUT("/sales")
	Call<Sale> update(@Body Sale data);

	@GET("/sales")
	Call<List<Sale>> findAll();
	
	@GET("/sales/{id}")
	Call<Sale> findById(@Path("id") long id);
}
```

[OrderApi](src/main/java/com/jdc/restaurant/client/api/OrderApi.java)
```java
public interface OrderApi {

	@POST("/orders/sale/{id}")
	Call<Order> create(@Path("id") long saleId, @Body Order order);

	@PUT("/orders/sale/{id}")
	Call<Order> update(@Path("id") long saleId, @Body Order order);

	@GET("/orders")
	Call<List<Order>> findAll();
	
	@GET("/orders/{id}")
	Call<Order> findById(@Path("id") long id);
}

```



