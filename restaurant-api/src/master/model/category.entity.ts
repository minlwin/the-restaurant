import { PrimaryGeneratedColumn, Entity, Column, Index, Exclusion, OneToMany } from "typeorm"
import { Product } from "./product.entity"
import { IdEnable } from "src/common/id.enable"

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