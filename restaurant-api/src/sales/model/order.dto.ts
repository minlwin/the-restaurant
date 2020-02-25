import { Sale } from "./sale.entity";
import { Product } from "src/master/model/product.entity";

export interface OrderDto {

    id:number
    sale:Sale
    product:Product
    unitPrice:number
    quantity:number
    status?:string
    remind?:number
    orderTime?:Date
}