package pl.edu.wat.timProject.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.edu.wat.timProject.dataModel.ClothesTypeEnum;
import pl.edu.wat.timProject.dataModel.hibernate.Clothes;
import pl.edu.wat.timProject.dataModel.hibernate.ClothesType;
import pl.edu.wat.timProject.dataModel.hibernate.Event;
import pl.edu.wat.timProject.dataModel.hibernate.Matched;
import pl.edu.wat.timProject.dataModel.hibernate.Tag;
import pl.edu.wat.timProject.dataModel.hibernate.Weather;
import pl.edu.wat.timProject.services.ClothesService;
import pl.edu.wat.timProject.services.MatchedService;

@Component
public class ClothesMatcher {
	@Autowired
	private ClothesService clothesService;
	@Autowired
	private MatchedService matchService;

	private Random random = new Random();
	private int temp;
	private Event event;

	private List<Clothes> bothClothes = new ArrayList<>();
	private List<Clothes> bottomClothes = new ArrayList<>();
	private List<Clothes> topClothes = new ArrayList<>();
	private List<Clothes> coverClothes = new ArrayList<>();

	public Matched addMatch() {
		Matched match = findMatch();
		matchService.register(match);

		return match;
	}

	private Matched findMatch() {
		List<Clothes> allClothes = clothesService.listAll();
		List<Clothes> matchedClothes = new ArrayList<>();
		Matched match = new Matched();
		match.setMatchedDate(new Date());
		match.setMatchedClothes(matchedClothes);

		List<Tag> tags = event.getEventsTag();
		List<ClothesType> clothesTypes = event.getEventsClothesTypes();

		rejectNotSuitableClothes(allClothes, tags, clothesTypes);
		if (allClothes.isEmpty()) {
			return match;
		}

		if (!bothClothes.isEmpty() && random.nextInt(10) > random.nextInt(6)) {
			matchedClothes.add(getClothesFromList(bothClothes));
			match.setMatchedClothes(matchedClothes);

			return match;
		}

		if (!bottomClothes.isEmpty()) {
			matchedClothes.add(getClothesFromList(bottomClothes));
		}
		if (!topClothes.isEmpty()) {
			matchedClothes.add(getClothesFromList(topClothes));
		}
		if (temp < 20 && !coverClothes.isEmpty()) {
			matchedClothes.add(getClothesFromList(coverClothes));
		}

		if (matchedClothes.isEmpty() && !bothClothes.isEmpty()) {
			matchedClothes.add(getClothesFromList(bothClothes));
		}

		/*
		 * for (Match oldMatch : matchService.listAll()) { if
		 * (shouldFindNewMatch(matchedClothes, match, oldMatch)) {
		 * 
		 * } }
		 */

		match.setMatchedClothes(matchedClothes);

		return match;
	}

	public boolean shouldFindNewMatch(List<Clothes> matchedClothes,
			Matched match, Matched oldMatch) {
		return oldMatch.getMatchedClothes().equals(matchedClothes)
				&& (oldMatch.getMatchedDate().equals(match.getMatchedDate()) || oldMatch
						.getRate() <= 2);
	}

	public Clothes getClothesFromList(List<Clothes> list) {
		int index = random.nextInt(list.size() - 1);

		return list.get(index);
	}

	public void rejectNotSuitableClothes(List<Clothes> allClothes,
			List<Tag> tags, List<ClothesType> clothesTypes) {
		Iterator<Clothes> clothesIterator = allClothes.iterator();
		while (clothesIterator.hasNext()) {
			Clothes clothes = clothesIterator.next();
			Iterator<ClothesType> clothesTypeIterator = clothesTypes.iterator();
			boolean iteratorRemoved = false;
			while (clothesTypeIterator.hasNext() && !iteratorRemoved) {
				ClothesType clothesType = clothesTypeIterator.next();
				if (isNotProperForWeather(clothesType.getWeather())) {
					clothesTypeIterator.remove();
					continue;
				}
				if (!clothesTypes.contains(clothes.getClothesType())) {
					clothesIterator.remove();
					iteratorRemoved = true;
				}
			}
			if (iteratorRemoved) {
				continue;
			}

			boolean hasAtLeastOneTag = false;
			for (Tag t : tags) {
				if (clothes.getClothesTags().contains(t)) {
					hasAtLeastOneTag = true;
					break;
				}
			}
			if (!hasAtLeastOneTag) {
				clothesIterator.remove();
				continue;
			}

			addClothesToProperList(clothes);
		}
	}

	public boolean isNotProperForWeather(Weather weather) {
		return weather.getTemperatureFrom() > temp
				|| weather.getTemperatureTo() < temp;
	}

	public void addClothesToProperList(Clothes clothes) {
		ClothesTypeEnum ctEnum = ClothesTypeEnum.values()[clothes
				.getClothesType().getClothesTypeValue()];
		switch (ctEnum) {
		case BOTH:
			bothClothes.add(clothes);
			break;

		case BOTTOM:
			bottomClothes.add(clothes);
			break;

		case COVER:
			coverClothes.add(clothes);
			break;

		case TOP:
			topClothes.add(clothes);
			break;
		}
	}

	public ClothesService getClothesService() {
		return clothesService;
	}

	public void setClothesService(ClothesService clothesService) {
		this.clothesService = clothesService;
	}

	public MatchedService getMatchService() {
		return matchService;
	}

	public void setMatchService(MatchedService matchService) {
		this.matchService = matchService;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
