SELECT
    CASE 
        WHEN EXISTS(SELECT S.CODE FROM SKILLCODES S WHERE S.CODE & D.SKILL_CODE AND S.NAME = 'Python' )
            AND EXISTS(SELECT S.CODE FROM SKILLCODES S WHERE S.CODE & D.SKILL_CODE AND S.CATEGORY = 'Front End') THEN 'A'
        WHEN EXISTS(SELECT S.CODE FROM SKILLCODES S WHERE S.CODE & D.SKILL_CODE AND S.NAME = 'C#') THEN 'B'
        WHEN EXISTS(SELECT S.CODE FROM SKILLCODES S WHERE S.CODE & D.SKILL_CODE AND S.CATEGORY = 'Front End') THEN 'C'
    ELSE 'D'
END AS GRADE, D.ID, D.EMAIL
FROM DEVELOPERS D
HAVING GRADE != 'D'
ORDER BY GRADE, ID