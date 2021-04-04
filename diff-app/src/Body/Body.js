import React, { useState } from "react";
import { generateDiff } from "../api/api";
import { GreenText, NormalText, RedText } from "../Utilities/Text";
import "./Body.css";

const Body = () => {
  const [originalText, setOriginalText] = useState("");
  const [modifiedText, setModifiedText] = useState("");
  const [diffText, setDiffText] = useState("");

  const loadDiff = (originalText, modifiedText) => {
    generateDiff(originalText, modifiedText).then((response) =>
      // console.log(response)
      setDiffText(response.data.diff)
    );
  };

  const compareBtnClickHandler = () => {
    loadDiff(originalText, modifiedText);
  };

  return (
    <div className="body">
      <div className="body__texts">
        <textarea
          placeholder="original text"
          value={originalText}
          onChange={(e) => setOriginalText(e.target.value)}
        ></textarea>
        <textarea
          placeholder="modified text"
          value={modifiedText}
          onChange={(e) => setModifiedText(e.target.value)}
        ></textarea>
      </div>
      <div className="body__compare">
        <button onClick={compareBtnClickHandler}>Compare</button>
      </div>
      {(originalText || modifiedText) && (
        <div className="body__diffResult">
          <div>
            {diffText.split("]").map((phrase) => {
              if (phrase[0] === "-") {
                return <RedText text={phrase.substring(2)} />;
              } else if (phrase[0] === "[") {
                return <NormalText text={phrase.substring(1)} />;
              }
            })}
          </div>
          <div>
            {diffText.split("]").map((phrase) => {
              if (phrase[0] === "+") {
                return <GreenText text={phrase.substring(2)} />;
              } else if (phrase[0] === "[") {
                return <NormalText text={phrase.substring(1)} />;
              }
            })}
          </div>
        </div>
      )}
    </div>
  );
};

export default Body;
