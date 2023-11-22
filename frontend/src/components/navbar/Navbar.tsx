import './Navbar.css';

export function Navbar() {
    return (
        <div className="navbar">
            <NavbarElement href={"/"} text={"Home"}/>
            <NavbarElement href={"/about"} text={"About"}/>
            <NavbarElement href={"/contact"} text={"Contact"}/>
        </div>
    );
}

export function NavbarElement(props: {href: string, text: string}) {
    return (
        <div className="navbar-element">
            <a className="navbar-link" href={props.href}>{props.text}</a>
        </div>
    )
}