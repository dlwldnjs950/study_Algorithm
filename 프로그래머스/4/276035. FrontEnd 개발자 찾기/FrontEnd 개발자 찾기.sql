SELECT DISTINCT ID, EMAIL, FIRST_NAME, LAST_NAME
FROM developers d
JOIN skillcodes s ON d.skill_code & s.code
-- JOIN skillcodes s ON d.skill_code = s.code
-- 등호를 쓰면 시간때매 틀리나보다...
WHERE s.category = 'Front End'
ORDER BY ID;

# 비트 연산자
# 저렇게 작성하면 ON 뒤에는 조건문이 오기 때문에 결과값이 0이 아닌 경우, 즉 SKILL_CODE에 CODE가 포함된 경우 조인이 성립합니다.