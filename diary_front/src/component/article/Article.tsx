import {useArticleGetter} from "./hooks/useArticleGetter";
import {useEffect} from "react";

export default function Article() {
    const { getArticles, articles } = useArticleGetter();

    useEffect(() => {
        getArticles();
    }, [getArticles]);

    console.log(articles);
    return (
        <>
            <p>article123</p>
        </>
    );
};