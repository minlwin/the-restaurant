# 2. Master Module

Application ကို အလုပ်လုပ်စေဖို့ ကြိုပြီးပြင်ထားပေးဖို့လိုအပ်တဲ့ Master Data တွေကို Manage လုပ်ဖို့အတွက် လိုအပ်တဲ့ Module ဖြစ်ပါတယ်။ အသုံးပြုမည့် Master Data တွေကတော့ Tables, Category နဲ့ Product တို့ပဲ ဖြစ်ပါတယ်။

```typescript
@Module({
    imports: [
        TypeOrmModule.forFeature([Category, Product, Tables])
    ],
    controllers: [
        CategoryController,
        ProductController,
        TablesController
    ],
    providers: [
        CategoryService,
        ProductService,
        TablesService
    ],
})
export class MasterModule {}
```

MasterModule ထဲမှာတော့ TypeOrmModule ကနေ Entity တွေအတွက် Repository တွေကို Inject လုပ်ဖို့အတွက် TypeOrmModule.forFeature([Category, Product, Tables]) ဆိုပြီး Import လုပ်ထားပါတယ်။ 

Module ထဲမှာ အသုံးပြုမည့် Controller တွေဖြစ်ကြတဲ့ CategoryController, ProductController, TablesController တွေကို Controller အနေနဲ့ Define လုပ်ထားပါတယ်။

CategoryService, ProductService, TablesService တွေကို Controller တွေထဲမှာ Inject လုပ်ပြီး အသုံးပြုနိုင်အောင် Provider အနေနဲ့ Define လုပ်ထားပါတယ်။

## Entities

Master Module ထဲမှာတော့ Master Data တွေဖြစ်ကြတဲ့ Category, Product, Tables Entity တွေပါဝင်ကြပါတယ်။

### Category Entity

ဟင်းအမျိုးအစားတွေကို ကိုယ်စားပြုတဲ့ Entity တစ်ခုဖြစ်ပါတယ်။ ဟင်းပွဲတွေကို ရှာဖွေရတာလွယ်ကူအောင် အကူအညီပေးနိုင်ပါတယ်။ တဖန် ဘယ်လိုဟင်းအမျိုးအစားတွေဟာ ဘယ်လို အခြေအနေမျိုးမှာ ရောင်းအားကောင်းလဲ ဆိုတာကို Analyse လုပ်လိုတဲ့ အခါမှာလဲ အသုံးဝင်ပါတယ်။

[Category](model/category.entity.ts)
```typescript
@Entity()
export class Category  implements IdEnable{
    
    @PrimaryGeneratedColumn()
    id:number

    @Index({unique : true})
    @Column()
    name:string
    @Column()
    color:number
}
```

### Product Entity

ဟင်းပွဲ Menu တွေကို ကိုယ်စားပြုတဲ့ Entity ဖြစ်ပါတယ်။ Category အလိုက် ဟင်းပွဲတွေကို သတ်မှတ်ထားလိုတဲ့ အတဝက် Category ကို ManyToOne နဲ့ Reference လုပ််ထားပါတယ်။

[Product](model/product.entity.ts)
```typescript
@Entity()
export class Product implements IdEnable{
    
    @PrimaryGeneratedColumn()
    id:number
    @Column()
    name:string

    @ManyToOne(type => Category, {
        nullable : false,
        eager: true
    })
    category:Category
    @Column()
    price:number
    @Column()
    size:String
}
```

### Tables Entity

Customer Table စားပွဲခုံတွေကို ကိုယ်စားပြုတဲ့ Entity Class ဖြစ်ပါတယ်။ ဘယ်စားပွဲကမှာတဲ့ ဟင်းလဲ၊ ဘယ်စားပွဲက ငွေရှင်းမှာလဲ ဆိုတဲ့နေရာမှာ အသုံးပြုမှာ ဖြစ်တယ်။

[Tables](model/tables.entity.ts)
```typescript
@Entity()
export class Tables implements IdEnable{
    @PrimaryGeneratedColumn()
    id:number
    @Index({unique : true})
    @Column()
    tableNumber:string
    @Column()
    seats:number
}
```

## Services

Service တွေဟာ Resouces တွေကို Handle လုပ်ပေးနိုင်တဲ့ Component တွေဖြစ်ကြပြီး Business တွေကို Wrap လုပ်ပေးနိုင်ပါတယ်။ MasterModule ထဲမှာတော့ CategoryService, ProductService နဲ့ TablesService တို့ပါဝင်ကြပါတယ်။

### Category Service

Category Resource အတွက် အခြေခံ CRUD Operation တွေကို လုပ်ဆောင်ပေးနိုင်ပါတယ်။ [BaseServiceMutable](/src/common/base.service.mutable.ts) Class ကို Extends လုပ်ထားခြင်းအားဖြင့် Super Class ထဲက Method  တွေကို Inheritance လုပ်ပြီး အသုံးပြုပါတယ်။

[CategoryService](model/category.service.ts)
```typescript
@Injectable()
export class CategoryService extends BaseServiceMutable<Category> {
    
    constructor(
        @InjectRepository(Category)
        repo:Repository<Category>) {
            super(repo)
        }
}
```

### Product Service

Produuct Resource အတွက် အခြေခံ CRUD Operation တွေကို လုပ်ဆောင်ပေးနိုင်ပါတယ်။ [BaseServiceMutable](/src/common/base.service.mutable.ts) Class ကို Extends လုပ်ထားခြင်းအားဖြင့် Super Class ထဲက Method  တွေကို Inheritance လုပ်ပြီး အသုံးပြုပါတယ်။

[ProductService](model/product.service.ts)
```typescript
@Injectable()
export class ProductService extends BaseServiceMutable<Product> {

    constructor(
        @InjectRepository(Product)
        repo:Repository<Product>
    ) { super(repo) }

    findByCategory(categoryId:number) {
        return this.repo.find({category : {id : categoryId}})
    }
}
```

တဖန် Product တွေကို Category Id ပေးပြီးရှာဖွေလိုတဲ့ အတွက် findByCategory() Method ကို ရေးသားထားပါတယ်။

### Tables Service

Tables Resource အတွက် အခြေခံ CRUD Operation တွေကို လုပ်ဆောင်ပေးနိုင်ပါတယ်။ [BaseServiceMutable](/src/common/base.service.mutable.ts) Class ကို Extends လုပ်ထားခြင်းအားဖြင့် Super Class ထဲက Method  တွေကို Inheritance လုပ်ပြီး အသုံးပြုပါတယ်။

[TablesService](model/tables.service.ts)
```typescript
@Injectable()
export class TablesService extends BaseServiceMutable<Tables> {

    constructor(
        @InjectRepository(Tables)
        repo:Repository<Tables>
    ) { super(repo) }

}
```

## Controllers

Master Data Resources တွေအတွက် API တွေဖြစ်ကြပါတယ်။ MasterModule ထဲမှာတော့ CategoryController, ProductController နဲ့ TablesController တို့ပါဝင်ကြပါတယ်။

### Category Controller

Category Resource တွေကို အသုံးပြုနိုင်တဲ့ End Point API ဖြစ်ပါတယ်။

```typescript
@Controller('categories')
export class CategoryController extends BaseControllerMutable<Category> {

    constructor(service:CategoryService) {
        super(service, '/categories')
    }
}
```

CategoryController ဟာ BaseControllerMutable ကို Extends လုပ်ထားတဲ့ အတွက် BaseControllerMutable မှာရှိတဲ့ Operation တွေကို အမွေဆက်ခံရရှိပါတယ်။ 

| Mapping Path | Request Method | Argument | Description |
|  ---  | --- | --- | --- |
| /categories | GET | | Category တွေအားလုံးကို ပြန်ပေးနိုင်မှာ ဖြစ်တယ် |
| /categories/:id | GET | id from path | Category Object ကို ID နဲ့ ရှာပေးနိုင်မှာ ဖြစ်တယ် |
| /categories | POST | Category From Body | Category တစ်ခုကို Create လုပ်တဲ့နေရာမှာ အသုံးပြုပါမယ် |
| /categories | PUT | Category From Body | Category တစ်ခုကို Update လုပ်တဲ့နေရာမှာ အသုံးပြုပါမယ် |

### Product Controller
Product Resource တွေကို အသုံးပြုနိုင်တဲ့ End Point API ဖြစ်ပါတယ်။

```typescript
@Controller('products')
export class ProductController extends BaseControllerMutable<Product> {

    constructor(private readonly productservice:ProductService) {
        super(productservice, '/products')
    }

    @Get('category/:id')
    findByCategory(@Param('id') categoryId:number) {
        return this.productservice.findByCategory(categoryId)
    }
}
```

CategoryController ဟာ BaseControllerMutable ကို Extends လုပ်ထားတဲ့ အတွက် BaseControllerMutable မှာရှိတဲ့ Operation တွေကို အမွေဆက်ခံရရှိပါတယ်။

တဖန် Category အောက်မှာရှိတဲ့ Product တွေကိုလဲ ရှာဖွေလိုတဲ့ အတွက် findByCategory() Method ကို ရေးသားထားပါတယ်။

| Mapping Path | Request Method | Argument | Description |
|  ---  | --- | --- | --- |
| /products | GET | | Product တွေအားလုံးကို ပြန်ပေးနိုင်မှာ ဖြစ်တယ် |
| /products/:id | GET | id from path | Product Object ကို ID နဲ့ ရှာပေးနိုင်မှာ ဖြစ်တယ် |
| /products | POST | Product From Body | Product တစ်ခုကို Create လုပ်တဲ့နေရာမှာ အသုံးပြုပါမယ် |
| /products | PUT | Product From Body | Product တစ်ခုကို Update လုပ်တဲ့နေရာမှာ အသုံးပြုပါမယ် |
| /products/category/:id | GET | id from path | Product တွေကို Category ID နဲ့ ရှာပေးနိုင်မှာ ဖြစ်တယ် |


### Tables Controller
Tables Resource တွေကို အသုံးပြုနိုင်တဲ့ End Point API ဖြစ်ပါတယ်။

```typescript
@Controller('tables')
export class TablesController extends BaseControllerMutable<Tables> {

    constructor(service:TablesService) {
        super(service, '/tables')
    }
}
```

CategoryController ဟာ BaseControllerMutable ကို Extends လုပ်ထားတဲ့ အတွက် BaseControllerMutable မှာရှိတဲ့ Operation တွေကို အမွေဆက်ခံရရှိပါတယ်။ 

| Mapping Path | Request Method | Argument | Description |
|  ---  | --- | --- | --- |
| /tables | GET | | Tables တွေအားလုံးကို ပြန်ပေးနိုင်မှာ ဖြစ်တယ် |
| /tables/:id | GET | id from path | Tables Object ကို ID နဲ့ ရှာပေးနိုင်မှာ ဖြစ်တယ် |
| /tables | POST | Tables From Body | Tables တစ်ခုကို Create လုပ်တဲ့နေရာမှာ အသုံးပြုပါမယ် |
| /tables | PUT | Tables From Body | Tables တစ်ခုကို Update လုပ်တဲ့နေရာမှာ အသုံးပြုပါမယ် |
