import {useNavigate} from "react-router-dom";

function App() {
    const navigator = useNavigate();

    function handleCreator() {
        navigator(`/article`);
    }

  return (
    <>
      <button onClick={handleCreator}>게시글</button>
    </>
  );
}

export default App;
