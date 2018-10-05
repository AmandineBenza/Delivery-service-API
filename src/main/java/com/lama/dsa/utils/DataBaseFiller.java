package com.lama.dsa.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lama.dsa.controller.IControllerHelper;
import com.lama.dsa.model.food.Food;
import com.lama.dsa.model.food.Menu;
import com.lama.dsa.model.order.Coursier;
import com.lama.dsa.model.order.EnumCoursierStatus;
import com.lama.dsa.model.order.Order;
import com.lama.dsa.model.order.EnumOrderStatus;
import com.lama.dsa.model.restaurant.Restaurant;;

@SuppressWarnings("deprecation")
public final class DataBaseFiller {

	private final static String[] SOURCES = { "./src/main/resources/databaseScenarios/Client_List.txt",
			"./src/main/resources/databaseScenarios/Food_List.txt",
			"./src/main/resources/databaseScenarios/Menus_List.txt",
			"./src/main/resources/databaseScenarios/Order_List.txt",
			"./src/main/resources/databaseScenarios/Restaurant_List.txt" };

	public static IControllerHelper helper;
	
	public static void fillDataBase() {
		DataBaseFiller.fillCoursiers();
		DataBaseFiller.fillFood();
		DataBaseFiller.fillMenu();
		DataBaseFiller.fillRestaurant();
		DataBaseFiller.fillOrder();
	}

	public static void fillCoursiers() {
		InputStream stream;

		try {
			// first file
			stream = new FileInputStream(SOURCES[0]);
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buff = new BufferedReader(reader);

			buff.readLine();
			buff.readLine();

			String line;
			String[] attributes;
			Coursier coursier;

			while ((line = buff.readLine()) != null) {
				attributes = line.split("\\|");
				coursier = new Coursier(Long.parseLong(attributes[0].trim().substring(1).trim()), attributes[1].trim(),Enum.valueOf(EnumCoursierStatus.class,attributes[2].trim()));

				try {
					helper.getCoursierService().insert(coursier);
				} catch (Exception ex) {
				}

				if (buff.readLine() == null) {
					break;
				}
			}

			buff.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void fillFood() {
		InputStream stream;

		try {
			stream = new FileInputStream(SOURCES[1]);
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buff = new BufferedReader(reader);

			buff.readLine();
			buff.readLine();

			String line;
			String[] attributes;
			Food food;

			while ((line = buff.readLine()) != null) {
				attributes = line.split("\\|");
				food = new Food(Long.parseLong(attributes[0].trim().substring(1).trim()),
						Long.parseLong(attributes[1].trim()), Float.parseFloat(attributes[2].trim()),
						attributes[3].trim(), attributes[4].trim());
				try {
					helper.getFoodService().insertFood(food);
				} catch (Exception ex) {
				}

				if (buff.readLine() == null) {
					break;
				}
			}

			buff.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void fillMenu() {
		InputStream stream;

		try {
			stream = new FileInputStream(SOURCES[2]);
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buff = new BufferedReader(reader);

			buff.readLine();
			buff.readLine();

			String line;
			String[] attributes;
			Menu menu;

			while ((line = buff.readLine()) != null) {
				attributes = line.split("\\|");
				List<Long> order = new ArrayList<>();
				order.add(Long.parseLong(attributes[4].trim()));
				order.add(Long.parseLong(attributes[5].trim()));

				menu = new Menu(Long.parseLong(attributes[0].trim().substring(1).trim()),
						Long.parseLong(attributes[1].trim()), Float.parseFloat(attributes[2].trim()),
						attributes[3].trim(), order);

				try {
					helper.getMenuService().insertMenu(menu);
				} catch (Exception ex) {
				}

				if (buff.readLine() == null) {
					break;
				}
			}

			buff.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void fillOrder() {
		InputStream stream;

		try {
			stream = new FileInputStream(SOURCES[3]);
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buff = new BufferedReader(reader);

			buff.readLine();
			buff.readLine();

			String line;
			String[] attributes;
			Order order;

			while ((line = buff.readLine()) != null) {
				attributes = line.split("\\|");
				List<Long> orderList = new ArrayList<>();
				orderList.add(Long.parseLong(attributes[7].trim()));
				Date dateStart = new Date();
				Date dateEnd = null;

				String[] splitStartDate = attributes[4].trim().split("\\s");
				String[] splitStartDay = splitStartDate[0].split("/");
				String[] splitStartHour = splitStartDate[1].split(":");

				dateStart.setDate(Integer.parseInt(splitStartDay[0].trim()));
				dateStart.setMonth(Integer.parseInt(splitStartDay[1].trim()));
				dateStart.setYear(Integer.parseInt(splitStartDay[2].trim()));
				dateStart.setHours(Integer.parseInt(splitStartHour[0].trim()));
				dateStart.setMinutes(Integer.parseInt(splitStartHour[1].trim()));

				if (!attributes[5].trim().contains("null")) {
					dateEnd = new Date();
					String[] splitEndDate = attributes[5].trim().split("\\s");
					String[] splitEndDay = splitEndDate[0].trim().split("/");
					String[] splitEndHour = splitEndDate[1].trim().split(":");

					dateEnd.setDate(Integer.parseInt(splitEndDay[0].trim()));
					dateEnd.setMonth(Integer.parseInt(splitEndDay[1].trim()));
					dateEnd.setYear(Integer.parseInt(splitEndDay[2].trim()));
					dateEnd.setHours(Integer.parseInt(splitEndHour[0].trim()));
					dateEnd.setMinutes(Integer.parseInt(splitEndHour[1].trim()));
				}

				order = new Order(Long.parseLong(attributes[0].trim().substring(1).trim()),
						Long.parseLong(attributes[1].trim()), Long.parseLong(attributes[2].trim()), "",
						Long.parseLong(attributes[3].trim()), dateStart, dateEnd,
						Enum.valueOf(EnumOrderStatus.class, attributes[6].trim()), orderList, 1L);

				try {
					helper.getOrderService().insertOrder(order);
				} catch (Exception ex) {
				}

				if (buff.readLine() == null) {
					break;
				}
			}

			buff.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void fillRestaurant() {
		InputStream stream;

		try {
			// first file
			stream = new FileInputStream(SOURCES[4]);
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buff = new BufferedReader(reader);

			buff.readLine();
			buff.readLine();

			String line;
			String[] attributes;
			Restaurant restaurant;

			while ((line = buff.readLine()) != null) {
				attributes = line.split("\\|");
				restaurant = new Restaurant(Long.parseLong(attributes[0].trim().substring(1).trim()), 0f,
						attributes[1].trim(), attributes[2].trim(), attributes[3].trim());

				try {
					helper.getRestaurantService().insertRestaurant(restaurant);
				} catch (Exception ex) {
				}
				if (buff.readLine() == null) {
					break;
				}
			}

			buff.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}