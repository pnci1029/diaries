import {useNavigate} from "react-router-dom";

function App() {
    const navigator = useNavigate();

    function handleArticle() {
        navigator(`/article`);
    }
    function handleTest() {
        navigator(`/websocket`);
    }

  return (
    <>
      <button onClick={handleArticle}>게시글</button>
      <button onClick={handleTest}>web</button>
    </>
  );
}

export default App;
