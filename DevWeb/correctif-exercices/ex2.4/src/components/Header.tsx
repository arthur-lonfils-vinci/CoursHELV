import "./Header.css";

interface HeaderProps {
  urlLogo: string;
  children: React.ReactNode;
}

const Header = (props: HeaderProps) => {
  return (
    <header className="header">
      <img src={props.urlLogo} alt="logo" className="logo" />
      <div>{props.children}</div>
    </header>
  );
};

export default Header;
