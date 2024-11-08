import { useState } from "react";
import "./Header.css";

interface HeaderProps {
  title: string;
  version: number;
  isPlaying?: boolean;
  onTogglePlayPause?: () => void;
}

const Header = ({
  title,
  version,
  isPlaying,
  onTogglePlayPause,
}: HeaderProps) => {
  const [menuPrinted, setMenuPrinted] = useState(false);

  const handleClick = () => {
    console.log(`value of menuPrinted before click: ${menuPrinted}`);
    setMenuPrinted(!menuPrinted);
  };

  const combinedClickHandler = () => {
    handleClick();
    if (onTogglePlayPause) {
      onTogglePlayPause();
    }
  };

  return (
    <header onClick={combinedClickHandler}>
      <h1 className="animate__animated animate__bounce">
        {menuPrinted ? `${title}... and rarely do we hate it!` : title}
        {isPlaying ? " (🎵)" : " (🔇)"}
        </h1>
      <h4>Version: {version}</h4>
    </header>
  );
};

export default Header;
