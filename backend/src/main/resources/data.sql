INSERT INTO lists (listtype, created, title, description, due, priority, status) VALUES ('Projectlist', '2023-08-02', 'Gute Liste', 'Dies ist ein Beispiel', '2023-02-18', 1, 1)
INSERT INTO lists (listtype, created, title, description, due, priority, status) VALUES ('Projectlist', '2023-10-02', 'Gute abgeschlossene Liste', 'Dies ist ein zweites Beispiel', '2023-02-18', 1, 1)
INSERT INTO lists (listtype, created, title, description, due, priority, status) VALUES ('Projectlist', '2023-10-02', 'Gute zweite Liste', 'Dies ist ein drittes Beispiel, das bleibt', '2023-02-18', 1, 1)
INSERT INTO task (created, description, due_date, status, title, lists_id) VALUES ( '2023-02-08','Viele Aufgaben', '2023-02-12',1, 'FSWD', 1)
INSERT INTO lists_task_list (lists_id, task_list_id) VALUES (1,1)
INSERT INTO lists (listtype, created, title, icon_name) VALUES ('Actionlist', '2023-08-02', 'Aktionsliste', 'card')
INSERT INTO task ( created, description, due_date, status, title, lists_id) VALUES ( '2023-02-08','Viele Aufgaben', '2023-02-12',1, 'Aktionslisteaufgabe', 4)
INSERT INTO lists_task_list (lists_id, task_list_id) VALUES (4,2)