import "./Header.css";

interface HeaderProps {
  urlLogo: string;
  children: React.ReactNode;
  theme: "light" | "dark";
}

const Header = ({urlLogo, children, theme}: HeaderProps) => {
  return (
    <footer className="header" style={{
      backgroundColor: theme === "dark" ? "black" : "white",
      color: theme === "dark" ? "white" : "black",
    }}>
      <img src={urlLogo} alt="logo" className="logo" />
      <div>{children}</div>
    </footer>
  );
};

export default Header;
