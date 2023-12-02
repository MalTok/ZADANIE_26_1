INSERT INTO ingredient
(name)
VALUES
    ('Śmietanka 36%'),
    ('Cukier puder'),
    ('Kakao'),
    ('Rodzynki'),
    ('Rum'),
    ('Małe beziki'),
    ('Ziemniaki'),
    ('Mąka pszenna'),
    ('Cebula'),
    ('Jajka'),
    ('Sól'),
    ('Olej roślinny');

INSERT INTO recipe
(title, description, portion, preparation, hints, img, date_added, likes, liked)
VALUES
    ('Krem sułtański',
     'Kultowy deser PRL',
     3,
     'Rodzynki przelać wrzątkiem na sicie i włożyć do miseczki, zalać rumem i odstawić na minimum godzinę.
     Śmietankę wlać do miski, wsypać cukier puder i ubić na gęstą, bitą śmietanę. Połowę bitej śmietany przełożyć do osobnej miski.
     Do odłożonej bitej śmietany przesiać kakao i na najmniejszych obrotach miksera zmiksować tylko do połączenia składników. Krem zgęstnieje, nie miksować go zbyt długo aby się nie zwarzył.
     Do pucharków na dno dać połowę pokruszonych bez i część rodzynek. Rodzynki wcześniej odcedzić. Nałożyć do pucharków po równo kremu kakaowego, następnie bitą śmietanę.
     Jeśli podajemy deser od razu, to posypujemy go resztą pokruszonych bez i rodzynek. Jeśli chłodzimy deser jeszcze przez 2-3 godziny, dekorujemy go zaraz przed podaniem.',
     'Krem sułtański można jeść zaraz po przygotowaniu, ale można (nawet lepiej) zostawić go w lodówce na 2-3 godziny,
     po tym czasie posypać bezami i rodzynkami.',
     'krem-sultanski.jpg',
     '2023-11-27 11:50:00',
     0,
     false),
    ('Placki ziemniaczane',
     'Tradycyjna potrawa lubiana przez dużych i małych',
     12,
     'Ziemniaki obrać i zetrzeć na tarce o małych oczkach bezpośrednio do większej i płaskiej miski. Zostawić je w misce bez mieszania, miskę delikatnie przechylić i odstawić tak na ok. 5 minut.
    W międzyczasie odlewać zbierający się sok, delikatnie przytrzymując ziemniaki, nadal ich nie mieszać. Na koniec docisnąć dłonią do miski i odlać jeszcze więcej soku. Dodać mąkę, drobno startą cebulę, jajko oraz dwie szczypty soli.
    Rozgrzać patelnię, wlać olej. Masę ziemniaczaną wymieszać. Nakładać porcje masy (1 pełna łyżka) na rozgrzany olej i rozprowadzać ją na dość cienki placek. Smażyć na średnim ogniu przez ok. 2 - 3 minuty na złoty kolor, przewrócić na drugą stronę i powtórzyć smażenie.
    Odkładać na talerz wyłożony ręcznikami papierowymi. Posypać solą morską z młynka. Placki ziemniaczane najlepsze są prosto z patelni gdy są chrupiące.',
     'Podawać z: sosem pieczarkowym / grzybowym / ajwarem / gulaszem / cukrem lub cukier + oddzielnie gęsta śmietana',
     'placki-ziemniaczane.jpg',
     '2023-11-28 20:26:20',
     0,
     false);

INSERT INTO ingredient_amount
(ingredient_id, recipe_id, amount)
VALUES
    (1L, 1L, '500 ml'),
    (2L, 1L, '30 g'),
    (3L, 1L, '20 g'),
    (4L, 1L, '50 g'),
    (5L, 1L, '70 ml'),
    (6L, 1L, '10 sztuk'),
    (7L, 2L, '1/2 kg'),
    (8L, 2L, '1/2 łyżki'),
    (9L, 2L, '1/4'),
    (10L, 2L, '1'),
    (11L, 2L, 'szczypta'),
    (12L, 2L, '2 łyżki');

INSERT INTO category
(name, description, img)
VALUES
    ('NA SŁODKO', 'Pyszne, tradycyjne ciasta i desery dla łasuchów.', 'na-slodko.jpg'),
    ('CIASTA', 'Torty, ciasta drożdzowe i inne słodkie wypieki.', 'ciasto.jpg'),
    ('DESERY', 'Lody, galaretki, kremy i wiele innych.', 'deser.jpg'),
    ('ZUPY', 'Polskie i zagraniczne specjały, sycące.', 'zupa.jpg'),
    ('DANIA GŁÓWNE', 'Pomysły na szybki i pożywny obiad dla całej rodziny.', 'danie-glowne.jpg'),
    ('SAŁATKI', 'Idealne na imprezy i nie tylko!', 'salatka.jpg'),
    ('PRZEKĄSKI', 'Na słodko i na słono.', 'przekaska.jpg'),
    ('NA CIEPŁO', 'Zawsze gdy masz ochotę na ciepły posiłek', 'na-cieplo.jpg'),
    ('INNE', 'Pyszne różności.', 'inne.jpg');

INSERT INTO recipe_categories
(recipes_id, categories_id)
VALUES
    (1L, 1L),
    (1L, 3L),
    (2L, 5L),
    (2L, 8L);





INSERT INTO ingredient
(name)
VALUES
    ('Pomidory'),
    ('Ogórki gruntowe'),
    ('Czerwona cebula'),
    ('Czarne oliwki'),
    ('Ser feta'),
    ('Suszone oregano'),
    ('Oliwa extra vergine'),
    ('Pieprz');

INSERT INTO recipe
(title, description, portion, preparation, hints, img, date_added, likes, liked)
VALUES
    ('Sałatka grecka',
     'Idealna przekąska lub samodzielne danie',
     4,
     'Pomidory pokroić na ćwiartki, usunąć szypułki, następnie pokroić na jeszcze mniejsze kawałki.
    Ogórki obrać (można pozostawić miejscami paseczki zielonej skórki), przekroić wzdłuż na pół a następnie na grubsze półplasterki.
    Cebulę cienko poszatkować. Oliwki przekroić na połówki.
    Wszystkie składniki umieścić w jednej większej salaterce lub w 4 mniejszych, doprawić świeżo zmielonym pieprzem.
    Na wierzchu położyć plasterki sera feta. Posypać suszonym oregano i polać oliwą. Można doprawić solą, ale ser feta jest już dość słony.',
     'Nie trzeba dodawać soli - feta jest wystarczająco słona.',
     'salatka-grecka.jpg',
     '2023-12-02 11:15:37',
     0,
     false);

INSERT INTO ingredient_amount
(ingredient_id, recipe_id, amount)
VALUES
    (13L, 3L, '2 sztuki'),
    (14L, 3L, '4 sztuki'),
    (15L, 3L, '1/2'),
    (16L, 3L, '1/2 szklanki'),
    (17L, 3L, '250 g'),
    (18L, 3L, '4 łyżeczki'),
    (19L, 3L, '4 łyżki'),
    (20L, 3L, 'szczypta');

INSERT INTO recipe_categories
(recipes_id, categories_id)
VALUES
    (3L, 6L),
    (3L, 7L);