import React from "react";
import "./Text.css";

export const RedText = ({ text }) => {
  return <div className="text__red">{text}</div>;
};

export const NormalText = ({ text }) => {
  return <div className="text__normal">{text}</div>;
};

export const GreenText = ({ text }) => {
  return <div className="text__green">{text}</div>;
};
