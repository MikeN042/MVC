import Alert from "./Components/Alert";
import Button from "./Components/Button";
import { useState } from "react";

function App() {
  const [alertVisibility, setAlertVisibility] = useState(false);

  return (
    <div>
      {alertVisibility && (
        <Alert onClose={() => setAlertVisibility(false)}>MyAlert</Alert>
      )}
      <Button color="secondary" onClick={() => setAlertVisibility(true)}>
        MyButton
      </Button>
    </div>
  );
}
export default App;
