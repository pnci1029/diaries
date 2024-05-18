import { useCallback, useState } from "react";
import { getArticlesAsync } from "../../store/articleSlice";
import { ArticleResponseDto } from "../types/Article";
import { useDispatch } from "react-redux";

interface ReturnType {
    getArticles: () => Promise<ArticleResponseDto>;
    articles: ArticleResponseDto | undefined;
}

export function useArticleGetter(): ReturnType {
    const dispatch = useDispatch<any>();

    const [article, setArticle] = useState<ArticleResponseDto | undefined>();

    const getArticles = useCallback(
        async () => {
            const result: ArticleResponseDto = await dispatch(getArticlesAsync()).unwrap();
            setArticle(result);
            return result;
        },
        [dispatch]
    );
    return {
        getArticles,
        articles: article,
    };
}