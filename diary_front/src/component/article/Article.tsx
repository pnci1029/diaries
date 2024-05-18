import {useArticleGetter} from "./hooks/useArticleGetter";
import {useEffect} from "react";
import {useNavigate} from "react-router-dom";

export default function Article() {
    const { getArticles, articles } = useArticleGetter();
    const navigator = useNavigate();

    useEffect(() => {
        getArticles();
    }, [getArticles]);

    function handleCreator() {
        navigator(`/article/create`);
    }

    console.log(articles);
    return (
        <>
            <p>article123</p>
            <button onClick={handleCreator}>post article</button>
        </>
    );
};