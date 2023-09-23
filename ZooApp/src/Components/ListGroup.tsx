import { useState } from "react";

function ListGroup() {
  let items = ["New York", "San Francisco", "Tokyo", "London", "Paris"];
  const [selectedIndex, setSelectedIndex] = useState(-1);

  return (
    <>
      <h1>List</h1>
      {items.length === 0 && <p>No List Items</p>}
      <ul className="list-group list-group-flush">
        {items.map((item, index) => (
          <li
            className={
              selectedIndex === index
                ? "list-group-item active"
                : "list-group-item"
            }
            key={item}
            onClick={() =>
              selectedIndex === index
                ? setSelectedIndex(-1)
                : setSelectedIndex(index)
            }
          >
            {item}
          </li>
        ))}
      </ul>
    </>
  );
}

export default ListGroup;
