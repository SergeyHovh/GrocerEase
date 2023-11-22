import {BasketData, ProductData} from "../../model/Client";
import {Product} from "./Product";
import './Customer.css'

export function Basket(props: { basketData: BasketData }) {
    return (
        <div className="basket-block-element">
            <h3>Basket #{props.basketData.id}</h3>
            {props.basketData?.products?.map((product: ProductData) => {
                return <Product productData={product} key={product.id}/>
            })}
        </div>
    )
}