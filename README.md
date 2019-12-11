# Cinema

# Описание задачи:

В этом задании вам нужно разработать простой веб сайт по покупки билетов в кинотеатр.

Начальная страница сайта index.html. 

На ней мы показываем Зал c рядами.

```
index.html

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Hello, world!</title>
</head>
<body>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<div class="container">
    <div class="row pt-3">
        <h4>
            Бронирование месте на сеанс
        </h4>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th style="width: 120px;">Ряд / Место</th>
                <th>1</th>
                <th>2</th>
                <th>3</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>1</th>
                <td><input type="radio" name="place" value="11"> Ряд 1, Место 1</td>
                <td><input type="radio" name="place" value="11"> Ряд 1, Место 1</td>
                <td><input type="radio" name="place" value="11"> Ряд 1, Место 1</td>
            </tr>
            <tr>
                <th>2</th>
                <td><input type="radio" name="place" value="11"> Ряд 2, Место 1</td>
                <td><input type="radio" name="place" value="11"> Ряд 2, Место 1</td>
                <td><input type="radio" name="place" value="11"> Ряд 2, Место 1</td>
            </tr>
            <tr>
                <th>3</th>
                <td><input type="radio" name="place" value="11"> Ряд 3, Место 1</td>
                <td><input type="radio" name="place" value="11"> Ряд 3, Место 1</td>
                <td><input type="radio" name="place" value="11"> Ряд 3, Место 1</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="row float-right">
        <button type="button" class="btn btn-success">Оплатить</button>
    </div>
</div>
</body>
</html>
```

1. Данные на index.html должны загружать через  Ajax. 

2. Для этого создайте Сервлет HallServlet. 

3. Если место занято, то нужно это отображать в таблице.

4. Страница должно обновлять периодически через timout.

5. После того, как пользователь выбрал место нужно перейти на страницу payment.html. Сделать это через window.local.href.

На странице нужно указать место и сумму денег.

```
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Hello, world!</title>
</head>
<body>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<div class="container">
    <div class="row pt-3">
        <h3>
            Вы выбрали ряд 1 место 1, Сумма : 500 рублей.
        </h3>
    </div>
    <div class="row">
        <form>
            <div class="form-group">
                <label for="username">ФИО</label>
                <input type="text" class="form-control" id="username" placeholder="ФИО">
            </div>
            <div class="form-group">
                <label for="phone">Номер телефона</label>
                <input type="text" class="form-control" id="phone" placeholder="Номер телефона">
            </div>
            <button type="button" class="btn btn-success">Оплатить</button>
        </form>
    </div>
</div>
</body>
</html>
```

1. У вас должно быть три слоя. Controller, Service, Persistence.

2. Создайте две таблицы. halls, accounts. 

3. В Persistence используйте jdbc. Важно. что вам нужно записи делать атомарно. То есть в одной транзакции - https://examples.javacodegeeks.com/core-java/sql/jdbc-nested-transactions-example/ 

___

+ Создана БД ***cine***. В ней- две таблицы:
  + ***dbHalls***
    + INSERT INTO dbSeHalls (hall_name, hall_rows, hall_seats, hall_price) VALUES (?, ?, ?, ?);
  + ***dbSellTickets***
    + INSERT INTO dbSellTickets (hall_name, customer_name, customer_phone, seat_row, seat_number) VALUES (?, ?, ?, ?, ?);
    
___

Три сервлета- 
***HallsForChoiceServlet*** - doGet возвращает список названий всех имеющихся в ***dbHalla*** залов.  
***HallServlet*** - doGet возвращает данные по конкретному кинозалу в ключая данные об уже купленных местах.  
***PayServlet*** - doPost принимает данные формы со страницы ***payment_page.html*** и добавляет данные в таблицу ***dbSellTickets***.  
