import logo from "../../assets/img/js-logo.png";
import "./Footer.css";

const Footer = () => {
    return (
        <footer>
            <h1 className="animate__animated animate__bounce animate__delay-2s">
                But we also love JS
            </h1>
            <img src={logo} alt="" />
        </footer>
    );
};

export default Footer;