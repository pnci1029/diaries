import {useArticleGetter} from "./hooks/useArticleGetter";

export default function Article() {
    const {getArticles} = useArticleGetter();
    console.log(getArticles)
    return (
        <>
            <p>article123</p>
        </>
    );
};