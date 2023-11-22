import api from "../api/Api";

export class ClientData {
    id: number
    username: string
    dateOfBirth: string
    baskets: Array<BasketData>

    constructor(id: number, username: string, dateOfBirth: string, baskets: Array<BasketData>) {
        this.id = id
        this.username = username
        this.dateOfBirth = dateOfBirth
        this.baskets = baskets
    }
}

export class BasketData {
    id: number
    products: Array<ProductData>

    constructor(id: number, products: Array<ProductData>) {
        this.id = id
        this.products = products
    }
}

export class ProductData {
    id: number
    product: Product
    amount: number

    constructor(id: number, product: Product, amount: number) {
        this.id = id
        this.product = product
        this.amount = amount
    }
}

export class Product {
    id: number
    productName: string
    price: number

    constructor(id: number, productName: string, price: number) {
        this.id = id
        this.productName = productName
        this.price = price
    }
}


export async function getClientData(): Promise<Array<ClientData>> {
    try {
        const response = await api.get("/api/v1/clients")
        return response.data
    } catch (error) {
        console.error(error)
        return []
    }
}