import {useArticleGetter} from "./hooks/useArticleGetter";

export default function Article() {
    const {getArticles} = useArticleGetter();
    return (
        <>
            <p>article123</p>
        </>
    );
};