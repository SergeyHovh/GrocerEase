import {ProductData} from "../../model/Client";
import './Customer.css'

export function Product(props: { productData: ProductData }) {
    return (
        <div className="product-block-element">
            <p>{props.productData.product.productName}</p>
            <p>{props.productData.product.price}</p>
            <p>{props.productData.amount}</p>
        </div>
    )
}