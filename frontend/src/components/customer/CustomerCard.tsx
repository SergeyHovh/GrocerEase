import {ClientData} from "../../model/Client";
import {Basket} from "./Basket";
import './Customer.css'

export function CustomerCard(props: { clientData: ClientData }) {
    return (
        <div className="client-block-element">
            <h2>{props.clientData.username}</h2>
            <p>{props.clientData.dateOfBirth}</p>
            {props.clientData?.baskets?.map((basket) => {
                return <Basket basketData={basket} key={basket.id}/>
            })}
        </div>
    )
}

