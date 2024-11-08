import "./Header.css";

interface HeaderProps {
    title: string;
}

const Header = (props: HeaderProps) => {
    return (
        <header key={props.title}>
            <h1 className="animate__animated animate__bounce">{props.title}</h1>
        </header>
    );
};

export default Header;