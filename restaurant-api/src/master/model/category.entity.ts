import { IdEnable } from "src/common/id.enable"
import { Column, Entity, Index, PrimaryGeneratedColumn } from "typeorm"

@Entity()
export class Category  implements IdEnable{
    
    @PrimaryGeneratedColumn()
    id:number

    @Index({unique : true})
    @Column()
    name:string
    @Column()
    colorCode?:string
    @Column()
    colorName?:string
}