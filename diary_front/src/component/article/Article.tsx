import {useArticleGetter} from "./hooks/useArticleGetter";

export default function Article() {
    const {getArticles, articles} = useArticleGetter();

    console.log(articles)
    return (
        <>
            <p>article123</p>
        </>
    );
};