import {useEffect, useState} from "react";
import {ClientData, getClientData} from "../../model/Client";
import {CustomerCard} from "../customer/CustomerCard";

export function AllCustomersCard() {

    const [clientData, setClientData] = useState(new Array<ClientData>())
    useEffect(() => {
        getClientData().then(data => setClientData(data));
    }, [])
    return (
        <>
            <h1>Clients</h1>
            <div className="client-block">
                {clientData.map((client) => {
                    return <CustomerCard clientData={client} key={client.id}/>
                })}
            </div>
        </>
    )
}

