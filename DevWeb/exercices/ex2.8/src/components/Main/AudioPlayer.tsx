import React, { useRef, useEffect } from "react";
import sound from "../../assets/sounds/Infecticide-11-Pizza-Spinoza.mp3";

interface AudioPlayerProps {
  isPlaying: boolean;
}

const AudioPlayer: React.FC<AudioPlayerProps> = ({ isPlaying }) => {
  const audioRef = useRef<HTMLAudioElement>(null);

  useEffect(() => {
    if (audioRef.current) {
      if (isPlaying) {
        audioRef.current.play();
      } else {
        audioRef.current.pause();
      }
    }
  }, [isPlaying]); // Run effect whenever `isPlaying` changes

  return <audio ref={audioRef} src={sound} />;
};

export default AudioPlayer;
