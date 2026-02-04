package dev.vuis.cccr.cloud.main;

public final class PacketIdListMain {
	private static int nextPacket = 10;

	private PacketIdListMain() {
	}

	static void main() {
		IO.println(getNextPacket() + " PacketClientRequest");
		IO.println(getNextPacket() + " PacketRequestedPlayerData");
		IO.println(getNextPacket() + " PacketRequestedInventory");
		IO.println(getNextPacket() + " PacketPartyCreate");
		IO.println(getNextPacket() + " PacketNotificationFromCloud");
		IO.println(getNextPacket() + " PacketRequestedFriends");
		IO.println(getNextPacket() + " PacketFriendRequest");
		IO.println(getNextPacket() + " PacketFriendRequestAccept");
		IO.println(getNextPacket() + " PacketFriendRemove");
		IO.println(getNextPacket() + " PacketInventorySetItemDefault");
		IO.println(getNextPacket() + " PacketRequestedItemDefault");
		IO.println(getNextPacket() + " PacketInventoryItemAdded");
		IO.println(getNextPacket() + " PacketInventoryOpenCase");
		IO.println(getNextPacket() + " PacketInventoryCaseResults");
		IO.println(getNextPacket() + " PacketFriendPoke");
		IO.println(getNextPacket() + " PacketRequestedTrading");
		IO.println(getNextPacket() + " PacketTradeRequestedUser");
		IO.println(getNextPacket() + " PacketTradeRequestUser");
		IO.println(getNextPacket() + " PacketTradeCreate");
		IO.println(getNextPacket() + " PacketTradeAction");
		IO.println(getNextPacket() + " PacketRequestedMarketValue");
		IO.println(getNextPacket() + " PacketMarketCreateListing");
		IO.println(getNextPacket() + " PacketRequestedMarketMyListings");
		IO.println(getNextPacket() + " PacketMarketMyListingAction");
		IO.println(getNextPacket() + " PacketMarketBrowseSearch");
		IO.println(getNextPacket() + " PacketMarketBrowseSearchResult");
		IO.println(getNextPacket() + " PacketMarketRequestCategory");
		IO.println(getNextPacket() + " PacketMarketRequestCategoryResult");
		IO.println(getNextPacket() + " PacketMarketListingAction");
		IO.println(getNextPacket() + " PacketServerMatchMaking");
		IO.println(getNextPacket() + " PacketServerNotification");
		IO.println(getNextPacket() + " PacketServerGameStatus");
		IO.println(getNextPacket() + " PacketMMSearch");
		IO.println(getNextPacket() + " PacketMMSearchCloudCanceled");
		IO.println(getNextPacket() + " PacketMMSearchHostCanceled");
		IO.println(getNextPacket() + " PacketRequestedCloudData");
		IO.println(getNextPacket() + " PacketInventoryItemDelete");
		IO.println(getNextPacket() + " PacketMessageSend");
		IO.println(getNextPacket() + " PacketMessageFromCloud");
		IO.println(getNextPacket() + " PacketInventoryItemRemoved");
		IO.println(getNextPacket() + " PacketRequestedMarketStoreItems");
		IO.println(getNextPacket() + " PacketMarketStoreOrder");
		IO.println(getNextPacket() + " PacketInventoryItemShowcase");
		IO.println(getNextPacket() + " PacketEditStatus");
		IO.println(getNextPacket() + " PacketPartyEditSettings");
		IO.println(getNextPacket() + " PacketPartyLeave");
		IO.println(getNextPacket() + " PacketPartyInvite");
		IO.println(getNextPacket() + " PacketPartyJoin");
		IO.println(getNextPacket() + " PacketPartyChatToCloud");
		IO.println(getNextPacket() + " PacketPartyChatToClient");
		IO.println(getNextPacket() + " PacketPartyJoined");
		IO.println(getNextPacket() + " PacketInventoryItemRename");
		IO.println(getNextPacket() + " PacketInventoryItemClearName");
		IO.println(getNextPacket() + " PacketServerMatchMakingAccepted");
		IO.println(getNextPacket() + " PacketMMJoinServer");
		IO.println(getNextPacket() + " PacketMarketMyListingSync");
		IO.println(getNextPacket() + " PacketPlayerDataIncreaseValue");
		IO.println(getNextPacket() + " PacketPlayerDataRandomDrop");
		IO.println(getNextPacket() + " PacketBanned");
		IO.println(getNextPacket() + " PacketClientUpdateStatus");
		IO.println(getNextPacket() + " PacketFriendJoinGame");
		IO.println(getNextPacket() + " PacketFriendSpectateGame");
		IO.println(getNextPacket() + " PacketInventoryContract");
		IO.println(getNextPacket() + " PacketInventoryBooster");
		IO.println(getNextPacket() + " PacketConsoleCommand");
		IO.println(getNextPacket() + " PacketConsoleMessage");
		IO.println(getNextPacket() + " PacketPartyKick");
		IO.println(getNextPacket() + " PacketRequestedPunishments");
		IO.println(getNextPacket() + " PacketCompetitiveAbandon");
		IO.println(getNextPacket() + " PacketCompetitiveRejoin");
		IO.println(getNextPacket() + " PacketCompetitiveLeave");
		IO.println(getNextPacket() + " PacketEditClanTag");
		IO.println(getNextPacket() + " PacketRequestCandy");
		IO.println(getNextPacket() + " PacketSendCandy");
		IO.println(getNextPacket() + " PacketInventoryOpenStickerCapsule");
		IO.println(getNextPacket() + " PacketInventoryStickerCapsuleResults");
		IO.println(getNextPacket() + " PacketInventoryItemApplySticker");
		IO.println(getNextPacket() + " PacketInventoryItemRemoveSticker");
		IO.println(getNextPacket() + " PacketToggleNotification_Poke");
		IO.println(getNextPacket() + " PacketToggleNotification_TradeRequests");
		IO.println(getNextPacket() + " PacketToggleNotification_FriendRequests");
		IO.println(getNextPacket() + " PacketTogglePrivacy_Profile");
		IO.println(getNextPacket() + " PacketTogglePrivacy_TradeRequests");
		IO.println(getNextPacket() + " PacketTogglePrivacy_FriendRequests");
		IO.println(getNextPacket() + " PacketToggleSupporter_Cape");
		IO.println(getNextPacket() + " PacketKickedPlayer");
		IO.println(getNextPacket() + " PacketCheckPrestige");
		IO.println(getNextPacket() + " PacketVoteEvasion");
		IO.println(getNextPacket() + " PacketFeedNew");
		IO.println(getNextPacket() + " PacketInventoryItemRedeem");
		IO.println(getNextPacket() + " PacketTwoFactorDisable");
		IO.println(getNextPacket() + " PacketTwoFactorEnable");
		IO.println(getNextPacket() + " PacketTwoFactorEnableRequest");
		IO.println(getNextPacket() + " PacketTwoFactorEnableResponse");
		IO.println(getNextPacket() + " PacketTwoFactorReminder");
		IO.println(getNextPacket() + " PacketLinkDiscord");
		IO.println(getNextPacket() + " PacketUnLinkDiscord");
		IO.println(getNextPacket() + " PacketLinkDiscordResponse");
	}

	private static int getNextPacket() {
		return nextPacket++;
	}
}
