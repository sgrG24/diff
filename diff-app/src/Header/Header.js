import React from "react";
import "./Header.css";

function Header() {
  return (
    <div className="header">
      <div className="header__image">
        <img
          src="https://raw.githubusercontent.com/fabiospampinato/vscode-diff/master/resources/logo.png"
          alt=""
        />
      </div>

      <div className="header__text">
        Diff
      </div>
    </div>
  );
}

export default Header;
