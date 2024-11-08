import "./App.css";
import Footer from "../Footer";
import Header from "../Header";
import Main from "../Main";
import { useState } from "react";

function App() {
  const [isPlaying, setIsPlaying] = useState(false);

  const togglePlayPause = () => {
    setIsPlaying(!isPlaying);
  };

  return (
    <div className="page">
      <Header
        title="We love Pizza"
        version={0 + 1}
        isPlaying={isPlaying}
        onTogglePlayPause={togglePlayPause}
      />
      <Main isAudioPlaying={isPlaying} />
      <Footer />
    </div>
  );
}

export default App;
