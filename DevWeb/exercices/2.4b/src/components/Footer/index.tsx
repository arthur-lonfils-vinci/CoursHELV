import "./Footer.css";

interface FooterProps {
  texte: string;
}

const Footer = (props: FooterProps) => {
  return (
    <footer key={props.texte}>
      <p>{props.texte}</p>
    </footer>
  );
};

export default Footer;
