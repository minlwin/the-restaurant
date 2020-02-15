# 1. Employee Module

Restaurant Application ကို အသုံးပြုမည့် ဝန်ထမ်းတွေကို Manage လုပ်တာတွေကို ဆောင်ရွက်ပေးမည့် Module ဖြစ်ပါတယ်။

```typescript
@Module({
    imports: [
        TypeOrmModule.forFeature([Employee])
    ],
    controllers: [
        EmployeeController, ],
    providers: [
        EmployeeService, ],
})
export class EmployeeModule {}
```
EmployeeModule ထဲမှာတော့ TypeOrmModule ကနေ Employee အတွက် Repository ကို Inject လုပ်ဖို့အတွက် TypeOrmModule.forFeature([Employee]) ဆိုပြီး Import လုပ်ထားပါတယ်။ တဖန် Controller အနေနဲ့ EmployeeController နဲ့ Inject လုပ်မည့် Provider Class အနေနဲ့ EmployeeService ကို အသုံးပြုမယ်လို့ ဖေါ်ပြတားပါတယ်။

## Entity

EmployeeModule ထဲမှာအသုံးပြုမည့် Entity ကတော့ ဝန်ထမ်းတွေကို ကိုယ်စားပြုမည့် Entity Class တစ်ခုရှိပါတယ်။

[Employee](model/employee.entity.ts)
```typescript
@Entity()
export class Employee implements IdEnable{
    
    @PrimaryGeneratedColumn()
    id:number
    @Column()
    name:string
    @Column()
    role:string
    @Column()
    email:string
    @Column()
    password:string
    @Column()
    phone:string
    @CreateDateColumn()
    @Transform(d => moment(d).format('YYYY-MM-DD HH:mm:ss'))
    creation:Date
    @UpdateDateColumn()
    @Transform(d => moment(d).format('YYYY-MM-DD HH:mm:ss'))
    modification:Date
}
```

## Services

Employee Resource ကို Handle လုပ်ပေးနိုင်တဲ့ EmployeeService Class ဖြစ်ပါတယ်။

[EmployeeService](model/employee.service.ts)
```typescript
@Injectable()
export class EmployeeService extends BaseServiceMutable<Employee> {
    constructor(
        @InjectRepository(Employee)
        repo:Repository<Employee>
    ) {
        super(repo)
    }

    findByNameLike(name:String) {
        return this.repo.createQueryBuilder()
            .where('LOWER(name) like :name', { name : `%${name.toLocaleLowerCase()}%` }).getMany()
    }
}
```

Provider တွေကနေ Inject လုပ်ပေးနိုင်အောင် @Injectable Decorator ကို ရေးသားထားပါတယ်။ တဖန် အခြေခံ CRUD Operation တွေကို အသုံးပြုနိုင်အောင် BaseServiceMutable Class ကို Extends လုပ်ထားပါတယ်။ BaseServiceMutable class ဟာ Generics Type ဖြစ်တဲ့အတွက် Type Parameter အနေနဲ့ Employee ကို ပေးထားတဲ့ အတွက် BaseServiceMutable ရဲ့ save(t:T), findAll():Promise<T[]> နဲ့ findById(id:number):Promise<T> method တွေရဲ့ T နေရာမှာ Employee က အစားထိုးဝင်ရောက်ပြီး Employee ကို အသုံးပြုနိုင်တဲ့ Operation တွေဖြစ်လာပါတယ်။

## Controllers

Employee Resource တွေကို အသုံးပြုနိုင်တဲ့ End Point API ဖြစ်ပါတယ်။

[Employee](controller/employee.controller.ts)
```typescript
@Controller("employees")
export class EmployeeController extends BaseControllerMutable<Employee> {

    constructor(private readonly empService:EmployeeService) {
        super(empService, '/employees')
    }

    @Get('search')
    @UseInterceptors(ClassSerializerInterceptor)
    search(@Query('name') name:string):Promise<Employee[]> {
        return this.empService.findByNameLike(name)
    }
}
```

EmployeeController ဟာ BaseControllerMutable ကို Extends လုပ်ထားတဲ့ အတွက် BaseControllerMutable မှာရှိတဲ့ Operation တွေကို အမွေဆက်ခံရရှိပါတယ်။ ဒါ့အပြင် Name နဲ့ Employee တွေကို ရှာနိုင်ဖို့လဲ EmployeeController ထဲမှာ search() Method ကို ရေးသားထားပါတယ်။

| Mapping Path | Request Method | Argument | Description |
|  ---  | --- | --- | --- |
| /employees | GET | | Employee တွေအားလုံးကို ပြန်ပေးနိုင်မှာ ဖြစ်တယ် |
| /employees/:id | GET | id from path | Employee Object ကို ID နဲ့ ရှာပေးနိုင်မှာ ဖြစ်တယ် |
| /employees | POST | Employee From Body | Employee တစ်ခုကို Create လုပ်တဲ့နေရာမှာ အသုံးပြုပါမယ် |
| /employees | PUT | Employee From Body | Employee တစ်ခုကို Update လုပ်တဲ့နေရာမှာ အသုံးပြုပါမယ် |
| /employees/search | GET | name From Query Param | Employee တွေကို name နဲ့ ရှာပေးနိုင်မှာ ဖြစ်တယ် |

