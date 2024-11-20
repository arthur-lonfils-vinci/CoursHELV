--2.a.ii

--1
SELECT au.au_fname
FROM authors au
WHERE au.city = 'Oakland';

--2
SELECT DISTINCT au.au_fname, au.address
FROM authors au
WHERE au.au_fname LIKE 'A%';

--3
SELECT DISTINCT au.au_fname, au.address
FROM authors au
WHERE au.phone IS NULL;

--4
SELECT DISTINCT au.au_fname
FROM authors au
WHERE au.state = 'CA' AND au.phone NOT LIKE '415%';

--5
SELECT DISTINCT au_fname
FROM authors au
WHERE au.country = 'BEL' OR au.country = 'NED' OR au.country = 'LUX';

--6
SELECT DISTINCT ti.pub_id
FROM titles ti
WHERE ti.type = 'psychology';

--7
SELECT DISTINCT ti.pub_id
FROM titles ti
WHERE ti.type = 'psychology' AND ti.price < 10 OR ti.price > 25;

--8
SELECT DISTINCT au.city
FROM authors au
WHERE au.state = 'CA' AND
      (au.au_lname LIKE '%er' OR au.au_fname = 'Albert');

--9
SELECT DISTINCT au.state, au.country
FROM authors au
WHERE au.state IS NOT NULL AND au.country <> 'USA';

--10
SELECT DISTINCT ti.type
FROM titles ti
WHERE ti.price < 15;



--2.b.iv

--10
SELECT DISTINCT p.pub_name
FROM publishers p,
     titles t,
     salesdetail sd,
     sales s
WHERE p.pub_id = t.pub_id
  and t.title_id = sd.title_id
  and sd.stor_id = s.stor_id
  and s.date BETWEEN '1990-11-01' AND '1991-03-01';

--11
SELECT DISTINCT s.stor_name
FROM stores s,
     titles t,
     salesdetail sd
WHERE LOWER(t.title) LIKE '%cook%'
  AND t.title_id = sd.title_id
  AND sd.stor_id = s.stor_id;

--12
SELECT t1.title AS title1, t2.title AS title2, t1.pub_id, t1.pubdate
FROM titles t1,
     titles t2
WHERE t1.title_id < t2.title_id
  AND t1.pub_id = t2.pub_id
  AND t1.pubdate = t2.pubdate;

--13
SELECT a.au_id, a.au_lname, a.au_fname
FROM authors a,
     titleauthor ta,
     titles t
WHERE a.au_id = ta.au_id
  AND ta.title_id = t.title_id
GROUP BY a.au_id, a.au_lname, a.au_fname
HAVING COUNT(DISTINCT t.pub_id) > 1;

--14
SELECT t.title
FROM titles t,
     salesdetail sd,
     sales s
WHERE t.title_id = sd.title_id
  AND sd.ord_num = s.ord_num
  AND s.date < t.pubdate;

--15
SELECT DISTINCT s.stor_name
FROM authors a,
     titleauthor ta,
     titles t,
     salesdetail sd,
     stores s,
     sales sa
WHERE a.au_id = ta.au_id
  AND ta.title_id = t.title_id
  AND t.title_id = sd.title_id
  AND sd.stor_id = s.stor_id
  AND s.stor_id = sa.stor_id
  AND a.au_lname = 'Ringer'
  AND a.au_fname = 'Anne';

--16
SELECT DISTINCT a.state
FROM authors a,
     titleauthor ta,
     titles t,
     salesdetail sd,
     sales s,
     stores st
WHERE a.au_id = ta.au_id
  AND ta.title_id = t.title_id
  AND t.title_id = sd.title_id
  AND sd.stor_id = s.stor_id
  AND s.stor_id = st.stor_id
  AND st.state = 'CA'
  AND s.date BETWEEN '1991-02-01' AND '1992-02-28';

--17
SELECT s1.stor_name AS store1, s2.stor_name AS store2, s1.state, a.au_id
FROM authors a,
     titleauthor ta,
     titles t,
     salesdetail sd1,
     stores s1,
     salesdetail sd2,
     stores s2
WHERE a.au_id = ta.au_id
  AND ta.title_id = t.title_id
  AND t.title_id = sd1.title_id
  AND sd1.stor_id = s1.stor_id
  AND t.title_id = sd2.title_id
  AND sd2.stor_id = s2.stor_id
  AND s1.state = s2.state
  AND s1.stor_id < s2.stor_id
GROUP BY s1.stor_name, s2.stor_name, s1.state, a.au_id;

--18
SELECT DISTINCT a1.au_lname AS author1, a2.au_fname AS author2
FROM titleauthor ta1,
     titleauthor ta2,
     authors a1,
     authors a2
WHERE ta1.au_id = a1.au_id
  AND ta2.au_id = a2.au_id
  AND ta1.au_id < ta2.au_id
  AND ta1.title_id = ta2.title_id;

--19
SELECT t.title,
       s.stor_name,
       t.price                   AS unit_price,
       sd.qty                    AS quantity_sold,
       (t.price * sd.qty)        AS total_amount,
       (t.price * sd.qty * 0.02) AS eco_tax
FROM titles t,
     salesdetail sd,
     stores s
WHERE t.title_id = sd.title_id
  AND sd.stor_id = s.stor_id;

--2.d.ii
--1
SELECT AVG(t.price) AS avg_price
FROM titles t,
     publishers p
WHERE t.pub_id = p.pub_id
  AND p.pub_name = 'Algodata Infosystems';

--2
SELECT a.au_fname, a.au_lname, AVG(t.price) AS avg_price
FROM authors a,
     titleauthor ta,
     titles t
WHERE a.au_id = ta.au_id
  AND ta.title_id = t.title_id
GROUP BY a.au_fname, a.au_lname;

--3
SELECT t.title, t.price, COUNT(ta.au_id) AS num_authors
FROM titles t,
     publishers p,
     titleauthor ta
WHERE p.pub_name = 'Algodata Infosystems'
  AND t.pub_id = p.pub_id
  AND t.title_id = ta.title_id
GROUP BY t.title, t.price;

--4
SELECT t.title, t.price, COUNT(DISTINCT sd.stor_id) AS num_stores
FROM titles t,
     salesdetail sd
WHERE t.title_id = sd.title_id
GROUP BY t.title, t.price;

--5
SELECT t.title
FROM titles t,
     salesdetail sd
WHERE t.title_id = sd.title_id
GROUP BY t.title
HAVING COUNT(DISTINCT sd.stor_id) > 1;

--6
SELECT t.type, COUNT(t.title_id) AS total_books, AVG(t.price) AS avg_price
FROM titles t
GROUP BY t.type;

--7
SELECT t.title_id, SUM(sd.qty) AS total_sales, t.total_sales AS recorded_total_sales
FROM titles t,
     salesdetail sd
WHERE t.title_id = sd.title_id
GROUP BY t.title_id, t.total_sales;

--8
SELECT t.title, SUM(sd.qty) AS total_sales, t.total_sales AS recorded_total_sales
FROM titles t,
     salesdetail sd
WHERE t.title_id = sd.title_id
GROUP BY t.title, t.total_sales
HAVING SUM(sd.qty) <> t.total_sales;

--9
SELECT t.title
FROM titles t,
     titleauthor ta
WHERE t.title_id = ta.title_id
GROUP BY t.title
HAVING COUNT(ta.au_id) >= 3;

--10
SELECT SUM(sd.qty) AS total_qty
FROM authors a,
     titleauthor ta,
     titles t,
     publishers p,
     salesdetail sd,
     stores s
WHERE a.state = 'CA'
  AND p.state = 'CA'
  AND s.state = 'CA';


--2.e.iv

--1
SELECT ti.*
FROM titles ti, publishers pu
WHERE ti.price = (SELECT max(ti1.price)
                     FROM titles ti1
                     WHERE ti1.pub_id = pu.pub_id
                     )
AND pu.pub_name = 'Algodata Infosystems';

--2
SELECT ti.title
FROM titles ti
WHERE ti.title_id  IN (SELECT sd.title_id
                       FROM salesdetail sd
                       WHERE sd.title_id = ti.title_id
                       GROUP BY sd.title_id
                       HAVING COUNT( DISTINCT sd.stor_id) > 1);

--3
SELECT ti.title
FROM titles ti
WHERE ti.price > (SELECT AVG(ti2.price)*1.5
                  FROM titles ti2
                  WHERE ti.type = ti2.type);


--5
SELECT pu.pub_name
FROM publishers pu
WHERE pu.pub_id NOT IN (SELECT DISTINCT ti.pub_id
                        FROM titles ti
                        );


--6
SELECT pu.pub_id, pu.pub_name
FROM publishers pu, titles ti
WHERE pu.pub_id = ti.pub_id
GROUP BY pu.pub_id, pu.pub_name
HAVING COUNT(ti.title_id) >= ALL (SELECT COUNT(ti1.title_id)
                                FROM titles ti1
                                GROUP BY ti1.pub_id
                                );


--7
SELECT pu.pub_name
FROM publishers pu
WHERE pu.pub_id IN ( SELECT DISTINCT ti.pub_id
                    FROM titles ti
                    WHERE ti.total_sales IS NULL
                    );


--8
SELECT DISTINCT t.title_id, t.title
FROM authors a
JOIN titleauthor ta ON a.au_id = ta.au_id
JOIN titles t ON ta.title_id = t.title_id
JOIN publishers p ON t.pub_id = p.pub_id
JOIN salesdetail sd ON t.title_id = sd.title_id
WHERE a.state = 'CA'
AND p.state = 'CA'
AND 'CA' = ALL (
    SELECT st.state
    FROM salesdetail sd2
    JOIN stores st ON sd2.stor_id = st.stor_id
    WHERE sd2.title_id = t.title_id
);


--9 : Quel est le titre du livre vendu le plus récemment ?
-- (S'il a des ex-aequo, donnez-les tous.)
SELECT DISTINCT ti.title, sa.date
FROM titles ti
JOIN salesdetail sd ON ti.title_id = sd.title_id
JOIN sales sa ON sd.stor_id = sa.stor_id AND sd.ord_num = sa.ord_num
WHERE sa.date = (SELECT MAX(sa1.date)
                 FROM sales sa1);


--10 : Quels sont les magasins où l'on a vendu (au moins)
-- tous les livres vendus par le magasin "Bookbeat" ?
SELECT DISTINCT st.*
FROM stores st
JOIN salesdetail sd ON sd.stor_id = st.stor_id
WHERE sd.title_id IN (SELECT DISTINCT sd1.title_id
                         FROM stores st1
                         JOIN salesdetail sd1 ON sd1.stor_id = st1.stor_id
                         WHERE st1.stor_name = 'Bookbeat')
AND st.stor_name <> 'Bookbeat';


--11 : Quelles sont les villes de Californie où
-- l'on peut trouver un auteur, mais aucun magasin ?
SELECT DISTINCT au.city
FROM authors au
WHERE au.city NOT IN ( SELECT st.city
                        FROM stores st
                        WHERE st.state = 'CA'
                       )
    AND au.state = 'CA';


--12 : Quels sont les éditeurs localisés dans la ville où
-- il y a le plus d'auteurs ?
SELECT pu.pub_name
FROM publishers pu
WHERE pu.city = (   SELECT au.city
                    FROM authors au
                    GROUP BY au.city
                    ORDER BY count(au.au_id) DESC
                    LIMIT 1
                );


--13 : Donnez les titres des livres
-- dont tous les auteurs sont californiens.
SELECT ti.title_id, ti.title
FROM titles ti
WHERE ti.title_id IN (  SELECT ta.title_id
                        FROM titleauthor ta, authors au
                        WHERE au.au_id = ta.au_id
                            AND au.state = 'CA'
                    );


--15 : Quels sont les livres
-- qui n'ont été écrits que par un seul auteur ?
SELECT ti.title_id, ti.title
FROM titles ti
WHERE ti.title_id IN (  SELECT ta.title_id
                        FROM titleauthor ta
                        GROUP BY ta.title_id
                        HAVING count(ta.au_id) = 1
                    );


--16 : Quels sont les livres qui n'ont qu'un auteur,
-- et tels que cet auteur soit californien ?
SELECT ti.title_id, ti.title
FROM titles ti
WHERE ti.title_id IN (  SELECT ta.title_id
                        FROM titleauthor ta, authors au
                        WHERE ta.au_id = au.au_id
                            AND au.state = 'CA'
                        GROUP BY ta.title_id
                        HAVING count(ta.au_id) = 1
                    );